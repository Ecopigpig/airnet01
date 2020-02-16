package com.zsc.servicedata.controller;

import com.zsc.servicedata.service.PollutionService;
import io.swagger.annotations.Api;
import model.pollutant.PollutionEpisode;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Api(value = "ExportController",tags = "Excel导出控制器")
@RestController
@RequestMapping("/export")
public class ExportController {

    @Autowired
    private PollutionService pollutionService;

    //尝试excel的导出
    @RequestMapping("/history")
    public void exportHistory(HttpServletResponse response) throws IOException{
        List<PollutionEpisode> pollutionEpisodeList = pollutionService.getPollutantHistory();

        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("获取excel测试表格");

        HSSFRow row;

        row = sheet.createRow(0);//创建第一个单元格
        row.setHeight((short) (36.25 * 20));
        row.createCell(0).setCellValue("全国各城市的污染情况历史记录");//为第一行单元格设值.

        /*为标题设计空间
         * firstRow从第1行开始
         * lastRow从第0行结束
         *
         *从第1个单元格开始
         * 从第3个单元格结束
         */
        CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 2);
        sheet.addMergedRegion(rowRegion);

      /*CellRangeAddress columnRegion = new CellRangeAddress(1,4,0,0);
      sheet.addMergedRegion(columnRegion);*/


        /*
         * 动态获取数据库列 sql语句 select COLUMN_NAME from INFORMATION_SCHEMA.Columns where table_name='user' and table_schema='test'
         * 第一个table_name 表名字
         * 第二个table_name 数据库名称
         * */
        row = sheet.createRow(1);
        row.setHeight((short) (22.50 * 20));//设置行高
        row.createCell(0).setCellValue("id");//为第一个单元格设值
        row.createCell(1).setCellValue("城市名称");
        row.createCell(2).setCellValue("空气质量");
        row.createCell(3).setCellValue("发布时间");//为第三个单元格设值
        row.createCell(4).setCellValue("AQI指数");
        row.createCell(5).setCellValue("首要污染物名称");
        row.createCell(6).setCellValue("so2浓度:ug/m3");//为第二个单元格设值
        row.createCell(7).setCellValue("pm2.5浓度:ug/m3");
        row.createCell(8).setCellValue("co浓度:ug/m3");
        row.createCell(9).setCellValue("no2浓度:ug/m3");
        row.createCell(10).setCellValue("pm10浓度:ug/m3");
        row.createCell(11).setCellValue("o3每8小时的平均浓度:ug/m3");



        for (int i = 0; i < pollutionEpisodeList.size(); i++) {
            row = sheet.createRow(i + 2);
            PollutionEpisode episode = pollutionEpisodeList.get(i);
            row.createCell(0).setCellValue(episode.getId()  );//为第一个单元格设值
            row.createCell(1).setCellValue(episode.getArea());
            row.createCell(2).setCellValue(episode.getQuality());
            row.createCell(3).setCellValue(episode.getCt());//为第三个单元格设值
            row.createCell(4).setCellValue(episode.getAqi());
            row.createCell(5).setCellValue(episode.getPrimaryPollutant());
            row.createCell(6).setCellValue(episode.getSo2());//为第二个单元格设值
            row.createCell(7).setCellValue(episode.getPm25());//为第三个单元格设值
            row.createCell(8).setCellValue(episode.getCo());
            row.createCell(9).setCellValue(episode.getNo2());
            row.createCell(10).setCellValue(episode.getPm10());
            row.createCell(11).setCellValue(episode.getO3Per8h());
        }

        sheet.setDefaultRowHeight((short) (16.5 * 20));
        //列宽自适应
        for (int i = 0; i <= 13; i++) {
            sheet.autoSizeColumn(i);
        }
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment;filename=user.xls");//默认Excel名称
        wb.write(os);
        os.flush();
        os.close();
    }
}
