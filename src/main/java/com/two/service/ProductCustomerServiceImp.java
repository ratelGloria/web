package com.two.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.two.common.Const;
import com.two.controller.ServerResponse;
import com.two.dao.CategoryMapper;
import com.two.dao.ProductMapper;
import com.two.pojo.Category;
import com.two.pojo.Product;
import com.two.utils.DateUtils;
import com.two.utils.PropertiesUtils;
import com.two.vo.ProductDetailVO;
import com.two.vo.ProductListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductCustomerServiceImp implements ProductCustomerService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CategoryService categoryService;

    @Override
    public ServerResponse list(Integer id,String keyword,Integer pageNum,Integer pageSize,String order) {
        System.out.println("-------keyword---"+keyword);
        if(id == null &&(keyword==null ||"".equals(keyword))){
            return ServerResponse.serverResponseByError("id和keyword不能同时为空");
        }
        if(id != null){

            Product product = new Product();
            product.setId(id);
            List<Product> products = productMapper.selectProductCondition(product);
            if(products.size()>0){
                PageHelper.startPage(pageNum,pageSize);
                ArrayList<ProductListVO> list = new ArrayList<>();
                for(Product p:products){
                    ProductListVO productListVO = getProductListVO(p);
                    list.add(productListVO);
                    }
                    PageInfo pageInfo = new PageInfo(list);
                 return ServerResponse.serverResponseBySuccess(pageInfo);
            }
        }
            Set<Integer> integerSet =new HashSet<>();
                    ServerResponse serverResponse = categoryService.selectCategoryAllChildId(id);
            if(serverResponse.getStatus() == 0){
                  integerSet = (Set<Integer>)serverResponse.getData();
            }
            if(keyword==null ||"".equals(keyword)){

            }

            if("".equals(order)){
                PageHelper.startPage(pageNum,pageSize);
            }else {
                 String[] orderArr = order.split("_");
                 if(orderArr.length>1){
                     PageHelper.startPage(pageNum,pageSize,orderArr[0]+" "+orderArr[1]);
                 }else {
                     PageHelper.startPage(pageNum,pageSize);
                 }
            }
            List<Product> products1 = productMapper.frontSearchCondition(integerSet, keyword);
            ArrayList<ProductListVO> list = new ArrayList<>();
            if(products1 != null && products1.size()>0){

                for(Product p:products1){
                    ProductListVO productListVO = getProductListVO(p);
                    list.add(productListVO);
                }
            }
            PageInfo pageInfo = new PageInfo(list);
            return ServerResponse.serverResponseBySuccess(pageInfo);


       /* return ServerResponse.serverResponseByError("暂无商品");*/
    }
    private ProductListVO getProductListVO(Product product){
        if(product == null){
            return null;
        }

        ProductListVO productListVO = new ProductListVO();
        productListVO.setCategoryId(product.getCategoryId());
        productListVO.setId(product.getId());
        productListVO.setMainImage(product.getMainImage());
        productListVO.setName(product.getName());
        productListVO.setStatus(product.getStatus());
        productListVO.setSubtitle(product.getSubtitle());
        productListVO.setPrice(product.getPrice());
        return productListVO;
    }

    @Override
    public ServerResponse detail(Integer id) {

        if(id == null){
            return ServerResponse.serverResponseByError("id不能为空");
        }
        Product product = new Product();
        product.setId(id);
        List<Product> products = productMapper.selectProductCondition(product);

        ArrayList<ProductDetailVO> list = new ArrayList<>();
        if(products.size()>0){

            for(Product p:products){

                if(p.getStatus() != Const.ProductStatusEnum.PRODUCT_ONLINE.getCode()){
                    ProductDetailVO productDetailVO = getProductDetailVO(p);
                    list.add(productDetailVO);
                }else {
                    return ServerResponse.serverResponseByError("该商品已下架");
                }
            }


            return ServerResponse.serverResponseBySuccess(list);
        }

        return ServerResponse.serverResponseByError("无匹配商品");
    }


    private ProductDetailVO getProductDetailVO(Product product){

        if(product == null){
            return null;
        }
        ProductDetailVO productDetailVO = new ProductDetailVO();
        productDetailVO.setCategoryId(product.getCategoryId());
        productDetailVO.setCreateTime(DateUtils.dateToStr(product.getCreateTime()));
        productDetailVO.setDetail(product.getDetail());
        productDetailVO.setImageHost(PropertiesUtils.readByKey("imageHost"));
        productDetailVO.setName(product.getName());
        productDetailVO.setPrice(product.getPrice());
        productDetailVO.setStock(product.getStock());
        productDetailVO.setUpdateTime(DateUtils.dateToStr(product.getUpdateTime()));
        productDetailVO.setSubImages(product.getSubImages());
        productDetailVO.setMainImage(product.getMainImage());
        productDetailVO.setStatus(product.getStatus());
        productDetailVO.setId(product.getId());
        productDetailVO.setSubtitle(product.getSubtitle());
        productDetailVO.setIsHot(product.getHot());
        productDetailVO.setIsNew(product.getNew());
        productDetailVO.setIsBanner(product.getBanner());

        Category category = categoryMapper.selectCategoryExit(product.getCategoryId());
        System.out.println("---------------"+category);
        if(category == null){
            return productDetailVO;
        }
        productDetailVO.setParentCategoryId(category.getId());
        return productDetailVO;
    }
}
