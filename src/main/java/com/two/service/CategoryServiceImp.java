package com.two.service;

import com.two.controller.ServerResponse;
import com.two.dao.CategoryMapper;
import com.two.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImp implements CategoryService{


    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public ServerResponse selectCategoryCondition(Integer categoryId) {

        if("".equals(categoryId)){

            return ServerResponse.serverResponseByError("类别不能为空");
        }
        Category category = new Category();
        category.setId(categoryId);

        List<Category> categories = categoryMapper.selectCategoryCondition(category);
        System.out.println("-----"+categories);
        if(categories == null || categories.size() == 0){
            return ServerResponse.serverResponseBySuccess("暂无商品类别");
        }
        Category category1 = new Category();
        category1.setParentId(categoryId);
        List<Category> categories1 = categoryMapper.selectCategoryCondition(category1);
        System.out.println("--------------"+categories1.size());
        if(categories1 == null || categories1.size() ==0){
            return ServerResponse.serverResponseByError("暂无子类别");
        }

        return ServerResponse.serverResponseBySuccess(categories1);

    }

    @Override
    public ServerResponse addCategory(Category category) {

        System.out.println("------------"+category.getName());
        if(category.getName() == null || "".equals(category.getName())){

            return ServerResponse.serverResponseByError("类别名不能为空");
        }

        List<Category> categories = categoryMapper.selectCategoryCondition(category);
        if(categories.size() != 0){
            return ServerResponse.serverResponseByError("类别已存在");
        }
        category.setStatus(1);
        int i = categoryMapper.addCategory(category);
        if(i>0){
            return ServerResponse.serverResponseBySuccess("添加成功");
        }

        return ServerResponse.serverResponseByError("添加失败");
    }

    @Override
    public ServerResponse updateCategory(Category category) {
        if(category.getId() == null || "".equals(category.getId())){
            return ServerResponse.serverResponseByError("ID不能为空");
        }
        if(category.getName() == null || "".equals(category.getName())){
            return ServerResponse.serverResponseByError("类别名不能为空");
        }

        int i = categoryMapper.updateCategory(category);
        if(i>0){
            return ServerResponse.serverResponseBySuccess("修改成功");
        }
        return ServerResponse.serverResponseByError("修改失败");
    }

    @Override
    public ServerResponse selectCategoryAllChildId(Integer id) {

        if(id == null || "".equals(id)){
            return ServerResponse.serverResponseByError("类别id不能为空");
        }
        HashSet<Integer> CategoryId = new HashSet<>();
        HashSet<Category> Categories = new HashSet<>();
        Set<Category> allChildCategory = findAllChildCategory(Categories, id);

        Iterator<Category> iterator = allChildCategory.iterator();

        while(iterator.hasNext()){
            Category next = iterator.next();
            CategoryId.add(next.getId());
        }

        return ServerResponse.serverResponseBySuccess(CategoryId);

    }

    @Override
    public Boolean selectCategoryExit(Integer id) {
        if(id == null ){
            return true;
        }

        Category category = categoryMapper.selectCategoryExit(id);
        System.out.println("+++++++++++="+category);
        if(category != null){
            return false;
        }
        return true;

    }

    private Set<Category> findAllChildCategory(Set<Category> categorySet,Integer id){
        Category category = categoryMapper.selectByPrimaryKey(id);
        if(category != null){
            categorySet.add(category);
        }

        List<Category> categories = categoryMapper.selectCategoryAllChildId(id);
        if(categories != null && categories.size()>0){

            for(Category c : categories){
                findAllChildCategory(categorySet,c.getId());
            }
            System.out.println("hhhhhhhhhh"+categories);
            return categorySet;
        }
        System.out.println("-------over-------"+categories);
        return categorySet;
    }




}
