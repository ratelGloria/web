package com.two.service;

import com.two.controller.ServerResponse;
import com.two.pojo.Category;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface CategoryService {

    public ServerResponse selectCategoryCondition(Integer categoryId);

    public ServerResponse addCategory(Category category);

    ServerResponse updateCategory(Category category);

    ServerResponse selectCategoryAllChildId(Integer id);

    Boolean selectCategoryExit(Integer id);



}
