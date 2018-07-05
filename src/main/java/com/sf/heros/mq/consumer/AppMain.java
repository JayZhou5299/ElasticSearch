package com.sf.heros.mq.consumer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sf.heros.mq.consumer.service.ElasticsearchService;
import com.sf.heros.mq.consumer.utils.APP;
import com.sf.heros.mq.consumer.vo.TaskInfo;

public class AppMain {

	private static final Logger logger = Logger.getLogger(AppMain.class);

	public void start() {
		ClassPathXmlApplicationContext context = null;
		try {
			context = new ClassPathXmlApplicationContext("classpath:app.xml");
		} catch (Exception e) {
			logger.error("An error occurred, applicationContext will close.", e);
			if (context != null) {
				context.close();
			}
			context = null;
			logger.error(APP.CLOSED_MSG);
		}
	}

	/**
	 * ����
	* @author �߹���
	* @date 2015��6��16�� ����10:14:21
	 */
	@Test
	public void insertNo() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:app.xml");
		ElasticsearchService service = context
				.getBean(ElasticsearchService.class);
		List<TaskInfo> taskInfoList = new ArrayList<TaskInfo>();
		for (int i = 0; i < 20; i++) {
			taskInfoList.add(new TaskInfo(String.valueOf((i + 5)), i + 5, "�߹���"
					+ i, "taskArea", "taskTags", i + 5, "1996-02-03", "������"));
		}
		service.insertOrUpdateTaskInfo(taskInfoList);
	}

	/**
	 * ��ѯ
	* @author �߹���
	* @date 2015��6��16�� ����10:14:21
	 */
	@Test
	public void serchNo() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:app.xml");
		com.sf.daidongxi.web.service.ElasticsearchService service = (com.sf.daidongxi.web.service.ElasticsearchService) context
				.getBean("es");
		List<Map<String, Object>> al = service.queryForObject("task_info",
				new String[] { "taskContent", "taskArea" }, "�߹���", "taskArea", SortOrder.DESC,
				0, 2);

		for (int i = 0; i < al.size(); i++) {
			System.out.println(al.get(i));
		}
		
	}
	
	/**
	 * filter��ѯ
	* @author �߹���
	* @date 2015��6��16�� ����10:14:21
	 */
	@Test
	public void serchFilter() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:app.xml");
		com.sf.daidongxi.web.service.ElasticsearchService service = (com.sf.daidongxi.web.service.ElasticsearchService) context
				.getBean("es");
		List<Map<String, Object>> al = service.queryForObjectForElasticSerch("task_info", "taskContent", "��",19,20);

		for (int i = 0; i < al.size(); i++) {
			System.out.println(al.get(i));
		}
		
	}
}
