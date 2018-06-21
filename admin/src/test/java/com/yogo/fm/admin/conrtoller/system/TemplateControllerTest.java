package com.yogo.fm.admin.conrtoller.system;

import com.yogo.fm.YogoAdminApplicationTest;
import com.yogo.fm.admin.controller.system.TemplateController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author xuqiangqiang
 * @Date: 2018/6/11 15:30
 * @Description:
 */
public class TemplateControllerTest extends YogoAdminApplicationTest {
    @Autowired
    private TemplateController templateController;
    @Test
    public void Test() throws Exception {
        //       accountController.importExcel("[{\"username\":\"zhaosi\",\"name\":\"赵四\",\"password\":\"37704467\",\"mobile\":\"15156183993\",\"salt\":\"1324124654\",\"roleName\":\"普通管理员\",\"roleId\":\"10002\"}]");
        templateController.export("10001","[{\"username\":\"zhaosi\",\"name\":\"赵四\",\"password\":\"37704467\",\"mobile\":\"15156183993\",\"salt\":\"1324124654\",\"roleName\":\"普通管理员\",\"roleId\":\"10002\",\"token\":\"45678797\"}]");
    }

    public static void main(String[] args){
//        Map<String,String> map=new HashMap<>();
//        map.put("k1","v1");
//        map.put("k2","v2");
//        for (String key:map.keySet()){
//            System.out.println(key+":"+map.get(key));
//        }
//
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + " ：" + entry.getValue());
//        }

        Map<String,String> map=new HashMap<>();
        map.put("b","b");
        map.put("c","a");
        map.put("a","c");
        List<Map.Entry<String,String>> list=new ArrayList<>(map.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String, String>>(){
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        for (Map.Entry<String, String> mapping : list) {
            System.out.println(mapping.getKey() + " ：" + mapping.getValue());
        }
    }

}

