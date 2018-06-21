package com.yogo.fm.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yogo.fm.annotation.ExcelFieldValue;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author qiqiang
 * @Description
 * @date 2018-06-04
 */
public class ExcelUtils {

    /**
     * 解析表头，格式如  name,姓名;age,年龄
     *
     * @param fields
     * @return
     */
    public static Map<String, String> titleName(String fields) {
        String[] fieldArray = fields.split(";");
        Map<String, String> map = new LinkedHashMap<>(fieldArray.length);
        for (String s : fieldArray) {
            if (StringUtils.isBlank(s) || !StringUtils.contains(s, ",")) {
                continue;
            }
            String[] mapArray = s.split(",");
            map.put(mapArray[0], mapArray[1]);
        }
        return map;
    }

    /**
     * 根据表头信息填充内容
     *
     * @param data
     * @param titles
     * @param <T>
     * @return
     */
    public static <T> List<List<String>> tableValues(List<T> data, String titles, Class<? extends T> clazz) throws Exception {
        return tableValues(data, titleName(titles), clazz);
    }

    /**
     * 根据表头信息填充内容
     *
     * @param data   数据库里的数据集
     * @param titles 格式如  key:name value:姓名
     * @param <T>
     * @return
     */
    public static <T> List<List<String>> tableValues(List<T> data, Map<String, String> titles, Class<? extends T> clazz) throws Exception {
        List<List<String>> table = new ArrayList<>();
        if (CollectionUtils.isEmpty(data)) {
            return table;
        }
        Field[] allFields = FieldUtils.getAllFields(clazz);
        Map<String, Field> fieldMap = new LinkedHashMap<>(allFields.length);
        for (Field field : allFields) {
            fieldMap.put(field.getName(), field);
        }
        Field field;
        //解析出来的行数据
        List<String> row;
        String key;
        Object fieldData;
        String fieldValue;
        //遍历数据
        for (T item : data) {
            row = new ArrayList<>();
            for (Map.Entry<String, String> entry : titles.entrySet()) {
                //获取表头key，如 name
                key = entry.getKey();
                //找出 key 字段
                field = fieldMap.get(key);
                if (field == null) {
                    continue;
                }
                fieldData = PropertyUtils.getProperty(item, key);
                //如果是日期，则按 YYYY-MM-dd HH:mm:ss 输出
                if (fieldData instanceof Date) {
                    fieldValue = DateUtils.dateToString((Date) fieldData);
                } else {
                    fieldValue = fieldData == null ? null : fieldData.toString();
                }
                row.add(fieldValue);
            }
            table.add(row);
        }
        return table;
    }

    /**
     * 根据中文名导入excel
     *
     * @param array 解析后的json数组
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> importTable(JSONArray array, Class<? extends T> clazz) throws Exception {
        List<T> list = new ArrayList<>();
        T entity;
        Field[] fieldsWithAnnotation = FieldUtils.getFieldsWithAnnotation(clazz, ExcelFieldValue.class);
        Map<String, Field> fieldMap = new LinkedHashMap<>(fieldsWithAnnotation.length);
        ExcelFieldValue annotation;
        for (Field field : fieldsWithAnnotation) {
            annotation = field.getAnnotation(ExcelFieldValue.class);
            fieldMap.put(annotation.value(), field);
        }
        for (Object item : array) {
            JSONObject object = (JSONObject) item;
            entity = clazz.newInstance();
            for (String key : object.keySet()) {
                Field field = fieldMap.get(key);
                if (field != null) {
                    PropertyUtils.setProperty(entity, field.getName(), object.get(key));
                }
            }
            list.add(entity);
        }
        return list;
    }

    /**
     * 生成excel
     *
     * @param fileName 文件名
     * @param data     表头
     * @param listDate 表数据
     * @return  HSSFWorkbook
     */
    public static HSSFWorkbook exportExcel(String fileName, String[][] data, List<List<String>> listDate){
        if (data.length < 1) {
            return null;
            // 第一步，创建一个webbook，对应一个Excel文件
        }
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(fileName);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();

        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); // 填充单元格

        //第五部：赋值
        HSSFCell cell;
        /*list.add("联系人栏（*为必填。如果字段类型为多选项的，各个选项用逗号隔开，且请确保各个选项与系统自定义的值匹配）");*/
        for (int i = 0; i < data[0].length; i++) {
            cell = row.createCell((short) i);
            cell.setCellValue(data[0][i]);
            sheet.setColumnWidth(i, data[0][i].getBytes().length * 2 * 300);
            String value = data[2][i];
            if (StringUtils.isNotBlank(value)) {
                //加载下拉列表内容
                DVConstraint constraint = DVConstraint.createExplicitListConstraint(value.split(","));
                // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
                CellRangeAddressList regions = new CellRangeAddressList(1, 200, i, i);
                // 数据有效性对象
                HSSFDataValidation dataValidationList = new HSSFDataValidation(regions, constraint);
                sheet.addValidationData(dataValidationList);
            }
        }


        // 最外面的循环遍历拿出第i列的数据
        if (!org.apache.commons.collections.CollectionUtils.isEmpty(listDate)) {
            for (int j = 0; j < listDate.size(); j++) {
                row = sheet.createRow(j + 1);
                List<String> list = listDate.get(j);
                //遍历第i行
                for (int o = 0; o < list.size(); o++) {
                    row.createCell((short) o).setCellValue(list.get(o) == null ? "" : list.get(o));
                }
                sheet.autoSizeColumn(j);
            }
        }
        return wb;

    }
}
