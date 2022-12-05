package org.zss.covidnineteenapi.controller;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zss.covidnineteenapi.entity.RiskAreaEntity;
import org.zss.covidnineteenapi.utils.HttpConfigUtil;
import org.zss.covidnineteenapi.utils.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController()
@RequestMapping("riskarea")
public class RiskAreaController {


    @GetMapping ("/selectlist")
    public R selectlist(String areaName){
        String url = "http://m."+areaName+".bendibao.com/news/yqdengji/";

        String htmlStr = HttpConfigUtil.getHttpResponse(url);
        Map<String,Object> map = new HashMap<>();
        Document doc = Jsoup.parse(htmlStr);
        //获取body元素，获取class="fc"的table元素
        Elements table = doc.body().getElementsByClass("cls1");
        //获取tbody元素=
        for (Element element:table){
            List<Node> els = new ArrayList<>();
            els.addAll(element.childNodes());
            els.remove(element.childNodeSize()-2);
            List<RiskAreaEntity> list = new ArrayList<>();
            for (Node node : els) {

                if (node.childNodeSize() == 0) {
                    continue;
                }
                RiskAreaEntity riskAreaEntity = new RiskAreaEntity();
//                System.out.println(node.childNodes().get(0).childNodes().get(0));
                riskAreaEntity.setAddress(String.valueOf(node.childNodes().get(1).childNodes().get(0)));
                riskAreaEntity.setGrade(String.valueOf(node.childNodes().get(3).childNodes().get(1).childNodes().get(0)));
                list.add(riskAreaEntity);
            }
            if (list.size() > 0) {
                if (list.get(0).getGrade().equals("高风险")) {
                    map.put("high-risk", list);
                }else if (list.get(0).getGrade().equals("中风险")){
                    map.put("Medium-risk", list);
                }else if (list.get(0).getGrade().equals("低风险")){
                    map.put("low-risk", list);
                }else {
                    continue;
                }

            }
        }
        return R.ok(map);
    }

}
