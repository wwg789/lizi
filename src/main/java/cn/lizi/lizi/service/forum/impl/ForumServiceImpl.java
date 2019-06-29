package cn.lizi.lizi.service.forum.impl;

import cn.lizi.lizi.common.ResultModel;
import cn.lizi.lizi.mapper.ForumMapper;
import cn.lizi.lizi.mapper.LoginMapper;
import cn.lizi.lizi.model.forum.ForumInfoModel;
import cn.lizi.lizi.model.forum.ForumParentDetailModel;
import cn.lizi.lizi.model.forum.UserCollectModel;
import cn.lizi.lizi.service.common.impl.CommonServiceImpl;
import cn.lizi.lizi.service.forum.ForumService;
import cn.lizi.lizi.service.other.CheckParamService;
import cn.lizi.lizi.utils.DateUtils4Java8;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ForumServiceImpl extends CommonServiceImpl implements ForumService {

    @Autowired()
    ForumMapper forumMapper;
    @Autowired
    LoginMapper loginMapper;
    @Autowired
    CheckParamService checkParamService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;

    /**
     * 获取发帖列表
     * @param model
     * @return
     */
    @Override
    public ResultModel queryForumList(ForumInfoModel model) {
        log.info("getForumList params {}",model);
        if(null == model.getPage()){
            model.setPage(0);
        }
        if(null == model.getPageSize()){
            model.setPageSize(10);
        }
        if(StringUtils.isEmpty(model.getForumContent())){
            model.setForumContent(null);
        }

        List<ForumInfoModel> forumList = new ArrayList<>();

        if(model.getTemp() == null){
            setQueryPage(model);
            forumList = forumMapper.queryForumList(model);
        }
        //最新
        else if(model.getTemp() == 0){
            setQueryPage(model);
            forumList = forumMapper.queryForumListZuiXin(model);
        }
        //热门
        else if (model.getTemp() == 1){
            setQueryPage(model);
            forumList = forumMapper.queryForumListReMen(model);
        }
        //精华
        else if (model.getTemp() == 2){
            setQueryPage(model);
            forumList = forumMapper.queryForumListJingHua(model);
        }

        forumList.forEach(k->{
            try {
                k.setChaTime(DateUtils4Java8.getTimeDiff(new Date(), k.getCreateTime()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return ResultModel.getPageData("成功",forumList);
    }

    /**
     *获取帖子详情
     * @param model
     * @return
     */
    @Override
    public ResultModel queryForumDetail(ForumInfoModel model){
        String resout = checkParamService.checkQueryForumDetail(model);
        if(StringUtils.isNotEmpty(resout)){
            return ResultModel.getError(resout);
        }
       log.info("getForumInfo params {}",model);
        ForumInfoModel forumInfo = forumMapper.queryForumDetail(model);

        //修改帖子访问次数
        forumMapper.updateForumSelectCount(model);
        return ResultModel.getSuccess("成功", forumInfo);
    }

    /**
     *修改帖子详情
     * @param model
     * @return
     */
    @Override
    public ResultModel updateForum(ForumInfoModel model) {
        int reoult = forumMapper.updateForum(model);
        if(reoult < 1){
            return ResultModel.getError("修改失败");
        }
        return ResultModel.getSuccess("成功",null);
    }

    /**
     *新建帖子
     * @param model
     * @return
     */
    @Override
    public ResultModel addForum(ForumInfoModel model) {

        String resout = checkParamService.checkAddForum(model);
        if(StringUtils.isNotEmpty(resout)){
            return ResultModel.getError(resout);
        }

        //初始参数
        model.setUserId(model.getUser().getId());
        model.setNickName(model.getUser().getNickName());
        model.setUserHeadPortraitUrl(model.getUser().getHeadPortraitUrl());
        model.setCreateTime(new Date());

        int reoult = forumMapper.addForum(model);
        if(reoult < 1){
            return ResultModel.getError("创建失败");
        }
        return ResultModel.getSuccess("成功",null);
    }

    /**
     *获取父级分类列表
     * @param model
     * @return
     */
    @Override
    public ResultModel queryForumParentList(ForumParentDetailModel model){
        return ResultModel.getSuccess("成功",forumMapper.queryForumParentList());
    }

    @Override
    public ResultModel queryForumListByZuiXin(ForumInfoModel model) {
        return null;
    }

    @Override
    public ResultModel queryForumListByReMen(ForumInfoModel model) {
        return null;
    }

    @Override
    public ResultModel queryForumListByJingHua(ForumInfoModel model) {
        return null;
    }

    /**
     * 添加收藏
     * @param model
     * @return
     */
    @Override
    public ResultModel addCollect(UserCollectModel model) {
        //参数校验
        String resout = checkParamService.checkAddCollect(model);
        if(StringUtils.isNotEmpty(resout)){
            return ResultModel.getError(resout);
        }

        int resoutData = forumMapper.addCollect(model);
        if(resoutData == 0){
            return ResultModel.getError("收藏失败");
        }
        //修改发帖收藏数量
        forumMapper.updateCollectCount(ForumInfoModel.builder().id(model.getForumId().toString()).build());
        return ResultModel.getSuccess("成功",null);
    }

    /**
     * 获取用户收藏列表
     * @param model
     * @return
     */
    @Override
    public ResultModel queryUserCollectList(ForumInfoModel model) {
        //参数校验 设置当前登陆用户ID
        String resout = checkParamService.checkQueryUserCollectParam(model);
        if(StringUtils.isNotEmpty(resout)){
            return ResultModel.getError(resout);
        }
        model.setUserId(model.getUser().getId());

        //查询用户收藏
        setQueryPage(model);
        List<ForumInfoModel> forumInfoModels = forumMapper.queryUserCollectList(model);


        return ResultModel.getPageData("成功",forumInfoModels);
    }

    /**
     * 查询用户发帖
     * @param model
     * @return
     */
    @Override
    public ResultModel queryUserWrite(ForumInfoModel model) {
        //参数校验 设置当前登陆用户ID
        String resout = checkParamService.checkQueryUserWriteParam(model);
        if(StringUtils.isNotEmpty(resout)){
            return ResultModel.getError(resout);
        }

        model.setUserId(model.getUser().getId());

        //查询用户发帖
        setQueryPage(model);
        List<ForumInfoModel> forumInfoModels = forumMapper.queryForumList(model);

        return ResultModel.getPageData("成功",forumInfoModels);
    }

    /**
     * 发帖图片上传
     * @param file
     * @return
     */
    @Override
    public ResultModel upload(MultipartFile file) {
        if(null == file){
            return ResultModel.getError("文件空");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        //时间戳
        String timeStr = System.currentTimeMillis()+"";
        //新的文件名称
        String resoutPath = "PIC-"+ timeStr +prefix;

        File pathfile = new File("/usr/local/tomcat/picture/"+ getFileSavePath(timeStr)+"/");
        if(!pathfile.exists()){
            pathfile.mkdirs();
        }
        try {
            file.transferTo(new File(pathfile+"/"+resoutPath));
        } catch (IOException e) {
            e.printStackTrace();
            return ResultModel.getResult(0,"上传异常",null);
        }

        return ResultModel.getResult(1,resoutPath,null);
    }

    /**
     * 获取帖子图片
     * @param picUrl
     * @return
     */
    @Override
    public ResultModel downloadPic(String picUrl) {
        if(StringUtils.isEmpty(picUrl)){
            return ResultModel.getError("文件空");
        }

        String path = "/usr/local/tomcat/picture/"+ getFileDownloadPath(picUrl)+ "/" + picUrl;

        try {
            FileInputStream in = new FileInputStream(new File(path));
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + UUID.randomUUID().toString().replaceAll("-","") + ".jpg");
            ServletOutputStream out = response.getOutputStream();
            int len = 0;
            byte buf[] = new byte[1024];
            out = response.getOutputStream();
            while( (len = in.read(buf)) > 0 ){
                //向客户端输出，实际是把数据存放在response中，然后web服务器再去response中读取
                out.write(buf, 0, len);
            }
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
