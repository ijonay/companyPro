package com.zc.utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xyzhuzhou on 2016/11/12 0012 12:20:59.
 */
public class EffectLog {

    /**
     * 程序名称
     */
    private String processName;
    private List<Object> keys = new ArrayList<>();
    private List<Long> vals = new ArrayList<>();

    public EffectLog(String processName) {
        this.processName = processName;
        add("Start!");
    }

    public void add(Object itemName) {
        keys.add(itemName);
        vals.add(System.currentTimeMillis());
    }

    public void writeToConsole() {


        Couter couter = new Couter();

        List<String> list = new ArrayList<>();

        for (int i = 0; i < keys.size(); i++) {

            String k = keys.get(i).toString();
            Long v = vals.get(i);

            couter.temp.add(v);

            String lineLog = "";
            if (couter.get() == 0) {
                lineLog = ("[" + processName + "][" + couter.get() + "]、[" + k + "]:" + v + "毫秒");

            } else {

                lineLog = ("[" + processName + "][" + couter.get() + "]、[" + k + "]:" + v + "毫秒" + "，与上一个相差"
                        + (v - vals.get(i - 1)) + "毫秒");

                if (couter.get() == (keys.size() - 1)) {


                    lineLog = ("[" + processName + "][" + couter.get() + "]、[" + k + "]:" + "总耗时："
                            + (v - couter.temp.get(0)) + "毫秒");

                }
            }

            list.add(lineLog);

            couter.add();


        }

        list.forEach(System.out::println);
        System.out.println("*****************************************************");


        try {
            list.add("*************************************************************");

            String path = this.getClass().getClassLoader().getResource("/").getPath();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String fileName = dateFormat.format(new Date()) + ".txt";

            FileHelper.write(path + "/effectlog/" + fileName, list);

        } catch (Exception ex) {

        }

    }


}
