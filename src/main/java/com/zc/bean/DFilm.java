package com.zc.bean;

/**
 * 
 */
public class DFilm {

    
    /**
     *  id ,所属表字段为d_film.id
     */
    private Integer id;
    
    /**
     *  filmNo ,所属表字段为d_film.filmno
     */
    private Integer filmNo;
    
    /**
     *  name ,所属表字段为d_film.name
     */
    private String name;
    
    /**
     *  areaTime ,所属表字段为d_film.areatime
     */
    private String areaTime;
    
    
    /**
     * 设置 表的  id 字段值
     * 
     * @param id
     *            将值赋予 d_film.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取  表的  id 字段
     * 
     * @return d_film.id
     */
    public Integer getId() {
        return  id;
    }
    
    /**
     * 设置 表的  filmNo 字段值
     * 
     * @param filmNo
     *            将值赋予 d_film.filmno
     */
    public void setFilmNo(Integer filmNo) {
        this.filmNo = filmNo;
    }

    /**
     * 获取  表的  filmNo 字段
     * 
     * @return d_film.filmno
     */
    public Integer getFilmNo() {
        return  filmNo;
    }
    
    /**
     * 设置 表的  name 字段值
     * 
     * @param name
     *            将值赋予 d_film.name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取  表的  name 字段
     * 
     * @return d_film.name
     */
    public String getName() {
        return  name;
    }
    
    /**
     * 设置 表的  areaTime 字段值
     * 
     * @param areaTime
     *            将值赋予 d_film.areatime
     */
    public void setAreaTime(String areaTime) {
        this.areaTime = areaTime;
    }

    /**
     * 获取  表的  areaTime 字段
     * 
     * @return d_film.areatime
     */
    public String getAreaTime() {
        return  areaTime;
    }
    
    
}