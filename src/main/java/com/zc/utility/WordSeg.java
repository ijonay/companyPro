package com.zc.utility;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuxiaodong on 2016/8/19 15:33.
 */
public class WordSeg {
    public List<String> input_lines, output_lines;

    public void read_input_file(String input_file_name) {
        File file = new File(input_file_name);
        String temp_str = "";

        input_lines = new ArrayList<String>();

        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), "UTF-8"));

            // dis.available() returns 0 if the file does not have more
            // lines.
            while ((temp_str = br.readLine()) != null) {
                // this statement reads the line from the file and print it
                // to the console.
                // temp_str = dis.readLine();
                input_lines.add(temp_str);
                // System.out.println(temp_str);
            }

            // dispose all the resources after using them.
            System.out.println("Total number of lines: "
                    + input_lines.size());
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write_output_file(String output_file_name) {
        File outfile = new File(output_file_name);
        String temp_str = "";

        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outfile), "UTF-8"));
            for (String str : output_lines) {
                bw.write(str);
                bw.write("\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parse_inputfile(String input_file_name,
                                String output_file_name) {
        File infile = new File(input_file_name);
        File outfile = new File(output_file_name);
        String temp_str = "";
        String parsed_comment = "";

        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(infile), "UTF-8"));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outfile), "UTF-8"));

            // dis.available() returns 0 if the file does not have more
            // lines.
            while ((temp_str = br.readLine()) != null) {
                // this statement reads the line from the file and print it
                // to the console.
                // temp_str = dis.readLine();
                // input_lines.add(temp_str);
                // System.out.println(temp_str);
                parsed_comment = "";

                List<Term> terms = NlpAnalysis.parse(temp_str).getTerms();
                for (Term term : terms) {
                    parsed_comment = parsed_comment + term.getName() + " ";
                }

                bw.write(parsed_comment);
                bw.write("\n");

            }

            // dispose all the resources after using them.
            // System.out.println("Total number of lines: " +
            // input_lines.size());
            br.close();
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // public static void main(String[] args) {
    // // TODO Auto-generated method stub
    // // 1. read input file name and output file name
    //
    // // String input_file_name = "����Ӱ��_ָ����3.txt";
    // // String output_file_name = "Douban.Ring3.parsed.txt";
    //
    // String input_file_name = args[0];
    // String output_file_name = args[1];
    //
    // /***
    // // 2. read input file content
    // read_input_file(input_file_name);
    //
    // // 3. parse the input file and write the result to the output file.
    // output_lines = new ArrayList<String>();
    //
    // for (String one_comment : input_lines){
    // String parsed_comment = "";
    // List<Term> terms = NlpAnalysis.parse(one_comment).getTerms();
    // for (Term term : terms){
    // parsed_comment = parsed_comment + term.getName() + " ";
    // }
    // output_lines.add(parsed_comment);
    // //System.out.println(output_lines.get(output_lines.size()-1));
    // }
    //
    // write_output_file(output_file_name);
    // ***/
    // parse_inputfile(input_file_name, output_file_name);
    //
    // /******
    // String one_line =
    // "����ģʽ���Ǵ�ͳ����ʽ����������Ϸ���ƽ������ĸ߳����ڶ��θ������������⣬�õ�Ӱ�ھ��ʵĹ����ﻹ�������˿����ھ����ȣ���Ȼ���ֵķ�ʽ�����⣬���㵽��ֹ����Ȼ�����붯��������������8�ַǳ��٣��ⲿ���ĳɹ����������������ں����빤ҵ��������˻��Ƕ�������ҫ�ü����ˡ� ";
    // List<Term> terms = NlpAnalysis.parse(one_line).getTerms();
    // for (Term one_term : terms){
    // System.out.print(one_term.getName() + " ");
    // }
    // System.out.println();
    //
    //
    // UserDefineLibrary.insertWord("����ʽ", "userDefine", 1000);
    //
    // String str =
    // "��������Ͻ���������ë�� ���ǿ���Ĥ���ʹ�������ܳ�һ������� ����ë���޸��Ŀ������� ��ݮ����ʷ��������û�� ���Ͳ��Ӳ����ɫ��Ƥ�����ǽ����� ����ʹ�ð�ȫ�����ı�ͬ������С�嵽ʮ�� 28������ӿ������ǵ���β��"
    // ;
    //
    // System.out.println(BaseAnalysis.parse(str));
    // System.out.println(ToAnalysis.parse(str));
    // System.out.println(DicAnalysis.parse(str));
    // System.out.println(IndexAnalysis.parse(str));
    // System.out.println(NlpAnalysis.parse(str));
    // *******/
    // }

}
