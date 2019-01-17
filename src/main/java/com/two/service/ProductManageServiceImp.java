package com.two.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.two.controller.ServerResponse;
import com.two.dao.CategoryMapper;
import com.two.dao.ProductMapper;
import com.two.pojo.Category;
import com.two.pojo.Product;
import com.two.utils.DateUtils;
import com.two.utils.FTPUtil;
import com.two.utils.PropertiesUtils;
import com.two.vo.ProductDetailVO;
import com.two.vo.ProductListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ProductManageServiceImp implements ProductManageService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public ServerResponse addProduct(Product product) {


        if(product.getCategoryId() == null || "".equals(product.getCategoryId())){
            return ServerResponse.serverResponseByError("CategoryId不能为空");
        }
        if(product.getName() == null || "".equals(product.getName())){
            return ServerResponse.serverResponseByError("名称不能为空");
        }
        if(product.getPrice() == null || "".equals(product.getPrice())){
            return ServerResponse.serverResponseByError("价格不能为空");
        }
        if(product.getStock() == null || "".equals(product.getStock())){
            return ServerResponse.serverResponseByError("库存不能为空");
        }


        if(product.getId() == null){

            Boolean aBoolean = categoryService.selectCategoryExit(product.getCategoryId());

            if(!aBoolean){
                Product product1 = new Product();
                product1.setName(product.getName());

                List<Product> products = productMapper.selectProductCondition(product1);
                System.out.println("---------"+ products+products.size());
                if(products.size() != 0){
                    return  ServerResponse.serverResponseByError("该商品已存在");
                }
                String subImages = product.getSubImages();
                String[] arrImages =null;
                if(subImages != null && !"".equals(subImages)){
                    arrImages = subImages.split(",");
                }
                if(subImages != null && subImages.length()>0){
                    product.setMainImage(arrImages[0]);
                }
                int insert = productMapper.insert(product);

                if(insert>0){
                    return  ServerResponse.serverResponseBySuccess("商品添加成功");
                }

                return ServerResponse.serverResponseByError("商品添加失败");
            }
            return ServerResponse.serverResponseByError("所选类别不存在");
        }else {

            int insert = productMapper.insert(product);

            if(insert>0){
                return ServerResponse.serverResponseBySuccess("更新成功");
            }
            return ServerResponse.serverResponseByError("更新失败");

        }

        }

    @Override
    public ServerResponse changeProductStatus(Integer id, Integer status) {

        if(id == null){
            return ServerResponse.serverResponseByError("id不为空");
        }
        if(status == null){
            return ServerResponse.serverResponseByError("status不能为空");
        }

        Product product = new Product();
        product.setId(id);

        List<Product> products = productMapper.selectProductCondition(product);
        if(products.size()==0){
            return ServerResponse.serverResponseByError("没有该商品");
        }
        product.setStatus(status);
        int i = productMapper.updateProductCondition(product);
        if(i>0){
            return ServerResponse.serverResponseBySuccess("状态修改成功");
        }
        return ServerResponse.serverResponseByError("状态修改失败");

    }

    @Override
    public ServerResponse getVoById(Integer id) {

        if(id == null){
            return ServerResponse.serverResponseByError("id不能为空");
        }

        Product product = productMapper.selectByPrimaryKey(id);

        if(product == null){
            return ServerResponse.serverResponseByError("不存在");
        }

        ProductDetailVO productDetailVO = getProductDetailVO(product);

        if(productDetailVO == null){
            return ServerResponse.serverResponseByError("不存在");
        }

        return ServerResponse.serverResponseBySuccess(productDetailVO);
    }

    @Override
    public ServerResponse list(Integer pageName, Integer pageSize) {
        PageHelper.startPage(pageName,pageSize);
        List<Product> products = productMapper.selectAll();
        List<ProductListVO> productListVOList = new ArrayList<>();
        if(products != null && products.size()>0){
            for(Product p:products){
                ProductListVO productListVO = getProductListVO(p);
                productListVOList.add(productListVO);
            }
        }
        PageInfo pageInfo = new PageInfo(productListVOList);
        return ServerResponse.serverResponseBySuccess(pageInfo);
    }

    @Override
    public ServerResponse search(Integer productId, String productName, Integer pageName, Integer pageSize) {
        Product product = new Product();
        product.setName(productName);
        product.setId(productId);
        /*PageHelper.startPage(pageName,pageSize);*/
        List<Product> products = productMapper.searchCondition(product);

        System.out.println("----------"+products);
        System.out.println("--productName--------"+productName);
        PageInfo pageInfo = new PageInfo(products);

        return ServerResponse.serverResponseBySuccess(pageInfo);
    }

    @Override
    public ServerResponse upload(MultipartFile file,String path) {

        if(file == null){
            return ServerResponse.serverResponseByError();
        }

        String originalFilename = file.getOriginalFilename();

        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));

        String newFileName = UUID.randomUUID().toString()+substring;

         File pathFile = new File(path);

         if(!pathFile.exists()){
             pathFile.setWritable(true);
             pathFile.mkdirs();
         }
        File file1 = new File(path, newFileName);

        try {
            file.transferTo(file1);
            //上传图片到服务器
            FTPUtil.uploadFile(Lists.newArrayList(file1));

            Map<String,String> map = Maps.newHashMap();

            map.put("uri",newFileName);
            map.put("url",PropertiesUtils.readByKey("imageHost")+"/"+newFileName);

            return ServerResponse.serverResponseBySuccess(map);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
        if(category == null){
            return null;
        }
        productDetailVO.setParentCategoryId(category.getId());
        return productDetailVO;
    }


}
