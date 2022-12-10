package org.zss.airiliveeventapi.controller;



import ch.qos.logback.core.util.FileUtil;
import com.alibaba.fastjson.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zss.airiliveeventapi.entity.LiveEventEntity;
import org.zss.airiliveeventapi.utils.FileUtils;
import org.zss.airiliveeventapi.utils.HttpConfigUtil;
import org.zss.airiliveeventapi.utils.R;

import java.util.*;


@RestController()
@RequestMapping("riskarea")
public class LiveEventController {


    @GetMapping ("/selectlist")
    public R selectlist(){
        String url = "http://mykwai.net/live/";

        String htmlStr = HttpConfigUtil.getHttpResponse(url,false);
        Map<String,Object> map = new HashMap<>();
        Document doc = Jsoup.parse(htmlStr);
        //获取body元素，获取class="fc"的table元素
        List<LiveEventEntity> list = new ArrayList<>();
        Elements table = doc.body().getElementsByClass("livelist");
        List<Node> els = table.get(0).childNodes();
        for (Node node : els) {

            if (node.childNodeSize() == 0) {
                continue;
            }
            LiveEventEntity liveEventEntity = new LiveEventEntity();
//                System.out.println(node.childNodes().get(0).childNodes().get(0));
            liveEventEntity.setDate(String.valueOf(node.childNodes().get(1).childNodes().get(1).childNodes().get(3).childNodes().get(0)));
            liveEventEntity.setText(String.valueOf(node.childNodes().get(1).childNodes().get(3).childNodes().get(0)));
            liveEventEntity.setType(String.valueOf(node.childNodes().get(1).childNodes().get(1).childNodes().get(1).childNodes().get(0)));
            StringBuffer sb = new StringBuffer(String.valueOf(node.childNodes().get(1).attributes().get("href")).replaceAll("\\.",""));
            sb.insert(sb.length()-4,"\\.");
            liveEventEntity.setUrl("http://mykwai.net"+ sb.toString().replaceAll("\\\\",""));


            list.add(liveEventEntity);
        }
        map.put("ds",list);
        return R.ok(map);
    }


}
