package cn.edu.scau.employee.dao;

import cn.edu.scau.employee.entity.Resource;
import cn.edu.scau.employee.mapper.ResourceExtendMapper;
import cn.edu.scau.employee.mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chen.jiale
 * @Description
 * @date 2019/12/22 22:29
 */
@Component
public class ResourceDao {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private ResourceExtendMapper resourceExtendMapper;

    public Resource findById(Long resourceId) {
        return resourceMapper.selectById(resourceId);
    }

    public List<Resource> findByParentId(Long parentId) {
        return resourceExtendMapper.selectByParentId(parentId);
    }

    public List<Resource> findByName(String name) {
        return resourceExtendMapper.selectByName(name);
    }

    public Long add(Resource resource) {
        resourceMapper.insert(resource);
        return resource.getId();
    }

    public int updateById(Resource parentResource) {
        return resourceMapper.updateById(parentResource);
    }

    public int getSubResourceNum(Long parentId) {
        return resourceExtendMapper.selectCountByParentId(parentId);
    }

    public int deleteByIds(List<Long> ids) {
        return resourceExtendMapper.deleteByIds(ids);
    }

    public List<Long> findIdByParentIds(List<Long> ids) {
        return resourceExtendMapper.selectIdByParentIds(ids);
    }

    public List<Resource> findAll() {
        return resourceExtendMapper.selectAll();
    }

    public int updateUrlById(String url, Long id) {
        return resourceExtendMapper.updateUrlById(url, id);
    }
}
