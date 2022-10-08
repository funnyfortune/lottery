package com.tc.draw.demo.api.org.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.tc.draw.demo.api.org.model.domain.SysPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tc.draw.demo.api.org.model.dto.post.SysPostListDTO;
import com.tc.draw.demo.api.org.service.ISysPostService;
import com.tc.draw.demo.common.constant.UserConstants;
import com.tc.draw.demo.common.utils.poi.ExcelUtil;

import com.tc.draw.demo.framework.web.controller.BaseController;
import com.tc.draw.demo.framework.web.model.Res;
import com.tc.draw.demo.framework.web.model.code.SysStatusCode;
import com.tc.draw.demo.framework.web.model.page.PageVO;

/**
 * 岗位信息操作处理
 * 
 *
 */
@RestController
@RequestMapping("/system/post")
public class SysPostController extends BaseController {
	@Autowired
	private ISysPostService postService;

	/**
	 * 获取岗位列表
	 */
	@PreAuthorize("@ss.hasPermi('system:post:list')")
	@GetMapping("/list")
	public Res<PageVO<SysPost>, Void> list(SysPostListDTO post) {
		startPage();
		List<SysPost> list = postService.selectPostList(post);
		return Res.ok(getDataTable(list));
	}

	// @OperateAction(title = "岗位管理", businessType = BusinessType.EXPORT)
	@PreAuthorize("@ss.hasPermi('system:config:export')")
	@GetMapping("/export")
	public Res<String, Void> export(SysPostListDTO post) {
		List<SysPost> list = postService.selectPostList(post);
		ExcelUtil<SysPost> util = new ExcelUtil<SysPost>(SysPost.class);
		return util.exportExcel(list, "岗位数据");
	}

	/**
	 * 根据岗位编号获取详细信息
	 */
	@PreAuthorize("@ss.hasPermi('system:post:list')")
	@GetMapping(value = "/{postId}")
	public Res<SysPost, Void> getInfo(@PathVariable Long postId) {
		return Res.ok(postService.selectPostById(postId));
	}

	/**
	 * 新增岗位
	 */
	@PreAuthorize("@ss.hasPermi('system:post:save')")
	// @OperateAction(title = "岗位管理", businessType = BusinessType.INSERT)
	@PostMapping("save")
	public Res<Integer, Void> add(@Validated @RequestBody SysPost post) {
		if (UserConstants.NOT_UNIQUE.equals(postService.checkPostNameUnique(post))) {
			return new Res<>(SysStatusCode.DB_EXIST_RECODE.getCode(), "新增岗位'" + post.getPostName() + "'失败，岗位名称已存在");
		} else if (UserConstants.NOT_UNIQUE.equals(postService.checkPostCodeUnique(post))) {
			return new Res<>(SysStatusCode.DB_EXIST_RECODE.getCode(), "新增岗位'" + post.getPostName() + "'失败，岗位编码已存在");
		}
		return Res.ok(postService.insertPost(post));
	}

	/**
	 * 修改岗位
	 */
	@PreAuthorize("@ss.hasPermi('system:post:update')")
	// @OperateAction(title = "岗位管理", businessType = BusinessType.UPDATE)
	@PostMapping("update")
	public Res<Integer, Void> edit(@Validated @RequestBody SysPost post) {
		if (UserConstants.NOT_UNIQUE.equals(postService.checkPostNameUnique(post))) {
			return new Res<>(SysStatusCode.DB_EXIST_RECODE.getCode(), "修改岗位'" + post.getPostName() + "'失败，岗位名称已存在");
		} else if (UserConstants.NOT_UNIQUE.equals(postService.checkPostCodeUnique(post))) {
			return new Res<>(SysStatusCode.DB_EXIST_RECODE.getCode(), "修改岗位'" + post.getPostName() + "'失败，岗位编码已存在");
		}
		return Res.ok(postService.updatePost(post));
	}

	/**
	 * 删除岗位
	 */
	@PreAuthorize("@ss.hasPermi('system:post:delete')")
	// @OperateAction(title = "岗位管理", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
	public Res<Integer, Void> remove(@RequestBody List<Long> postIds) {
		// 判断岗位是否被使用
		List<String> usedIds = postService.checkPostIdsIsUsed(postIds);
		if (usedIds != null && usedIds.size() > 0) {
			String idString = usedIds.stream().collect(Collectors.joining(", ", "[", "]"));
			return Res.error(SysStatusCode.DB_EXIST_RECODE.getCode(), "岗位" + idString + "存在用户，不能删除");
		}
		return Res.ok(postService.deletePostByIds(postIds));
	}

	/**
	 * 获取岗位选择框列表
	 */
	@GetMapping("/optionselect")
	public Res<List<SysPost>, Void> optionselect() {
		List<SysPost> posts = postService.selectPostAll();
		return Res.ok(posts);
	}
}
