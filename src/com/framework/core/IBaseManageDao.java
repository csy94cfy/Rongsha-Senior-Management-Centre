package com.framework.core;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 数据库基础操作 dao 基类
 */
public interface IBaseManageDao<T> {
	/**
	 * 通过Id获取单条数据
	 */
	T getById(@Param("id") String id);

	/**
	 * 获取条件获取单条数据
	 */
	T get(T entity);

	/**
	 * 查询数据列表，如果需要分页，请设置分页对象。 
	 * 需结合PageHelper插件 如：entity.setPage(new Page<T>());
	 */
	List<T> list(T entity);

	/**
	 * 查询所有数据列表
	 */
	List<T> listAll();

	/**
	 * 新增一条数据
	 */
	int insert(T entity);

	/**
	 * 更新一条数据
	 */
	long update(T entity);

	/**
	 * 通过条件删除数据 逻辑删除 更新del_flag=1 
	 * 底层使用update实现
	 */
	long delete(T entity);

	/**
	 * 通过id删除数据 逻辑删除 
	 * 更新del_flag=1
	 */
	int deleteById(@Param("id") String id, @Param("updateBy") String updateBy);

	/**
	 * 通过id 批量删除数据 
	 * 逻辑删除 更新del_flag=1
	 */
	long deleteByIds(@Param("ids") String[] ids, @Param("updateBy") String updateBy);

	/**
	 * 通过条件统计查询
	 */
	long count(T entity);
}
