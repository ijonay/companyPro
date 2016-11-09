package com.zc.model.topicsearch;

import java.util.*;

/**
 * Created by xyzhuzhou on 2016/11/7 0007 11:28:00.
 */
public class SearchModel {

    private ArrayList<Integer> age = new ArrayList();
    private ArrayList<Integer> gender = new ArrayList();
    private ArrayList<Integer> education = new ArrayList();
    private ArrayList<Integer> area = new ArrayList();
    private ArrayList<Integer> eventClass = new ArrayList();
    private ArrayList<Integer> userClass = new ArrayList();

    /**
     * 获取FilterId集合
     *
     * @return
     */
    public Set<Integer> getFilterIds() {

        HashSet<Integer> filterIds = new HashSet<>();

        if (Objects.nonNull(gender) && gender.size() > 0)
            gender.forEach(p -> filterIds.add(p));

        if (Objects.nonNull(education) && education.size() > 0)
            education.forEach(p -> filterIds.add(p));

        if (Objects.nonNull(area) && area.size() > 0)
            area.forEach(p -> filterIds.add(p));

        if (Objects.nonNull(eventClass) && eventClass.size() > 0)
            eventClass.forEach(p -> filterIds.add(p));

        if (Objects.nonNull(userClass) && userClass.size() > 0)
            userClass.forEach(p -> filterIds.add(p));

        return filterIds;
    }

    public ArrayList<Integer> getAge() {
        return age;
    }

    public void setAge(ArrayList<Integer> age) {
        this.age = age;
    }

    public ArrayList<Integer> getGender() {
        return gender;
    }

    public void setGender(ArrayList<Integer> gender) {
        this.gender = gender;
    }

    public ArrayList<Integer> getEducation() {
        return education;
    }

    public void setEducation(ArrayList<Integer> education) {
        this.education = education;
    }

    public ArrayList<Integer> getArea() {
        return area;
    }

    public void setArea(ArrayList<Integer> area) {
        this.area = area;
    }

    public ArrayList<Integer> getEventClass() {
        return eventClass;
    }

    public void setEventClass(ArrayList<Integer> eventClass) {
        this.eventClass = eventClass;
    }

    public ArrayList<Integer> getUserClass() {
        return userClass;
    }

    public void setUserClass(ArrayList<Integer> userClass) {
        this.userClass = userClass;
    }
}
