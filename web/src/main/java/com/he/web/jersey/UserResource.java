package com.he.web.jersey;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.he.model.entity.sys.SysUser;
import com.he.model.enums.StatusEnum;
import com.he.service.sys.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Component
//@Path uri路径
@Path("/user")
@Slf4j
//TODO 日志待统一
public class UserResource {
    @Resource
    private IUserService userService;

    //TODO 可定义JerseyUtil(响应的工具类),统一调用返回

    @GET
    @Path("/{id}")
    //@Produces 指定返回MIME格式
    @Produces(MediaType.APPLICATION_JSON)
    public Response findUserById(@PathParam("id")Long id){
        SysUser user = userService.selectById(id);
        if(user!=null && user.getStatus().equals(StatusEnum.STATUS_NORMAL)) {
            //OK(200, “OK”), 请求成功
            return Response.ok(user).build();
        }
        //NO_CONTENT(204, “No Content”),无返回数据
        return Response.status(Response.Status.NO_CONTENT).build();
        //或者 return Response.noContent();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllUsers(){
        SysUser u = new SysUser();
        u.setStatus(StatusEnum.STATUS_NORMAL);
        List<SysUser> userlist= userService.selectList(new EntityWrapper(u));
        return Response.ok(userlist).build();

    }

    @POST
    @Path("/add")
    //@Consumes 指定接受的MIME格式
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(SysUser user) {

        SysUser u = new SysUser();
        u.setAccount(user.getAccount());
        u.setStatus(StatusEnum.STATUS_NORMAL);
        if(userService.selectOne(new EntityWrapper(u))!=null) {
            log.info("user already exits.");
            return Response.status(Response.Status.CONFLICT).build();
        }
        userService.insert(user);
        return Response.created(URI.create("/user/" + user.getId())).build();
    }

    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(SysUser user) {
        userService.updateById(user);
        return Response.ok(user).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteArticle(@PathParam("id") Long id) {
        userService.deleteById(id);
        return Response.noContent().build();
    }
}
