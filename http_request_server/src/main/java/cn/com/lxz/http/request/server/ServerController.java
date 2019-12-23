package cn.com.lxz.http.request.server;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * http请求服务测试端
 */
@RestController
public class ServerController {

    /**
     * 1、GET请求，无参
     * @return
     */
    @GetMapping("/getRequestWithNoParam")
    public String getRequestWithNoParam(){
        return "GET请求--无参数 请求成功！";
    }

    /**
     * 2、GET请求，参数格式：拼接url
     * @return
     */
    @GetMapping("/getRequestWithUrlParam")
    public String getRequestWithUrlParam(HttpServletRequest request, @RequestParam String name, @RequestParam String age){
        String token = request.getHeader("token");
        System.out.println("===============GET请求，参数格式：拼接url=====================");
        return "{姓名："+name+"，年龄："+age+",token:"+token+"}";
    }

    /**
     * 3、GET请求，参数格式：使用URI获得HttpGet
     * @return
     */
    @GetMapping("/getRequestWithUriParam")
    public String getRequestWithUriParam(@RequestParam String name,@RequestParam String age){
        System.out.println("===============GET请求，参数格式：使用URI获得HttpGet=====================");
        return "{姓名："+name+"，年龄："+age+"}";
    }

    /**
     * 4、POSt请求 无参
     * @return
     */
    @PostMapping("/postRequestWithNoParam")
    public String postRequestWithNoParam(){
        return "POST请求--无参数 请求成功！";
    }

    /**
     * 5、POST请求 参数格式：url拼接
     * @return
     */
    @PostMapping("/postRequestWithUrlParam")
    public String postRequestWithUrlParam(HttpServletRequest request, @RequestParam String name, @RequestParam String age){
        String token = request.getHeader("token");
        System.out.println("===============POST请求 参数格式：url拼接=====================");
        return "{姓名："+name+"，年龄："+age+",token:"+token+"}";
    }


    /**
     * 6、POST请求，参数格式:json
     * @return
     */
    @PostMapping("/postRequestWithJson")
    //（1）、String类型接收
    public String postRequestWithJson(@RequestBody String param){
        JSONObject jsonObject = JSON.parseObject(param);
        String name = jsonObject.getString("name");
        return "欢迎你，"+name;
    }

    //（2）、JSONObject类型接收
    /*public String postRequestWithJson(@RequestBody JSONObject param){
        String name = param.getString("name");
        return "欢迎你，"+name;
    }*/

    //（3）、实体类型接收
   /* public String postRequestWithJson(@RequestBody User param){
        String name = param.getName();
        return "欢迎你，"+name;
    }*/





}
