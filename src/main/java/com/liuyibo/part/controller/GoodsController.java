package com.liuyibo.part.controller;

import com.liuyibo.part.dao.GoodsRepository;
import com.liuyibo.part.entity.Goods;
import com.liuyibo.part.utils.JPushUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
public class GoodsController {
    @Autowired
    GoodsRepository repository;
    @Autowired
    Goods goods;
    @RequestMapping("/selectAll")
    public @ResponseBody List<Goods> selectAll(){
        return repository.findAll();
    }
    @RequestMapping("/findOne")
    public @ResponseBody Goods selectOne(long id){
        return repository.findOne(id);
    }
    @RequestMapping("/delete")
    public @ResponseBody String deleteOne(long id){
        try{
            repository.delete(id);
            JPushUtil.sendPush("有新的货物变更,请下拉刷新");
        }catch (Exception e){
            return "0";
        }
        return "1";
    }
    @RequestMapping("/addnew")
    public @ResponseBody String addNew (@RequestBody Goods goods){
        System.out.println(goods.toString());
        try{
            repository.save(goods);
            JPushUtil.sendPush("有新的货物变更："+goods.getName());
        }catch (Exception e){
            return "0";
        }
        return "1";
    }
    @RequestMapping("/recover")
    public @ResponseBody String recover(@RequestParam("file") MultipartFile file,
                                        HttpServletRequest request) throws IOException {
        String line;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            isr = new InputStreamReader(file.getInputStream());
            br = new BufferedReader(isr);
            while((line = br.readLine())!=null){
                goods = Goods.stringToGoods(line);
                repository.save(goods);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "failure";
        }
        finally {
            br.close();
            isr.close();
        }
        return "success";
    }
    @RequestMapping("/backup")
    public void downloadFile(HttpServletRequest request,HttpServletResponse response){
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + "back.txt");
        String line = null;
        try{

            OutputStream ops = response.getOutputStream();
            OutputStreamWriter opsw = new OutputStreamWriter(ops);
            BufferedWriter bw = new BufferedWriter(opsw);
            List<Goods> goodsList = repository.findAll();
            for(Goods goods: goodsList){
                line = Goods.goodsToString(goods);
                bw.write(line);
                bw.newLine();
            }
            bw.close();
            opsw.close();
            ops.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
