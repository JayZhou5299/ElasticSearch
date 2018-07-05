package com.sf.daidongxi.web.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.queries.TermFilter;
import org.apache.lucene.queryparser.xml.builders.FilteredQueryBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolFilterBuilder;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.RangeFilterBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import sun.misc.Contended;

public class ElasticsearchService implements InitializingBean {

	private static final Logger logger = Logger
			.getLogger(ElasticsearchService.class);

	@Autowired
	private Client client;

	private String esIndexName = "heros";

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	private Client esClient;

	/** ��ѯ id */
	public List<String> queryId(String type, String[] fields, String content,
			String sortField, SortOrder order, int from, int size) {
		SearchRequestBuilder reqBuilder = client.prepareSearch(esIndexName)
				.setTypes(type).setSearchType(SearchType.DEFAULT)
				.setExplain(true);
		QueryStringQueryBuilder queryString = QueryBuilders.queryString("\""
				+ content + "\"");
		for (String k : fields) {
			queryString.field(k);
		}
		queryString.minimumShouldMatch("10");
		reqBuilder.setQuery(QueryBuilders.boolQuery().should(queryString))
				.setExplain(true);
		if (StringUtils.isNotEmpty(sortField) && order != null) {
			reqBuilder.addSort(sortField, order);
		}
		if (from >= 0 && size > 0) {
			reqBuilder.setFrom(from).setSize(size);
		}
		SearchResponse resp = reqBuilder.execute().actionGet();
		SearchHit[] hits = resp.getHits().getHits();
		ArrayList<String> results = new ArrayList<String>();
		for (SearchHit hit : hits) {
			results.add(hit.getId());
		}
		return results;
	}

	/**
	 * ��ѯ�õ����ΪMap����
	 * 
	 * @author �߹���
	 * @date 2015��6��15�� ����8:46:13
	 * @param type
	 *            ��
	 * @param fields
	 *            �ֶ�����
	 * @param content
	 *            ��ѯ��ֵ
	 * @param sortField
	 *            ������ֶ�
	 * @param order
	 *            �����Ҏ�t
	 * @param from
	 *            ���
	 * @param size
	 * @return
	 */
	public List<Map<String, Object>> queryForObject(String type,
			String[] fields, String content, String sortField, SortOrder order,
			int from, int size) {
		SearchRequestBuilder reqBuilder = client.prepareSearch(esIndexName)
				.setTypes(type).setSearchType(SearchType.DEFAULT)
				.setExplain(true);
		QueryStringQueryBuilder queryString = QueryBuilders.queryString("\""
				+ content + "\"");
		for (String k : fields) {
			queryString.field(k);
		}
		queryString.minimumShouldMatch("10");
		reqBuilder.setQuery(QueryBuilders.boolQuery().should(queryString))
				.setExplain(true);
		if (StringUtils.isNotEmpty(sortField) && order != null) {
			reqBuilder.addSort(sortField, order);
		}
		if (from >= 0 && size > 0) {
			reqBuilder.setFrom(from).setSize(size);
		}

		SearchResponse resp = reqBuilder.execute().actionGet();
		SearchHit[] hits = resp.getHits().getHits();

		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		for (SearchHit hit : hits) {
			results.add(hit.getSource());
		}
		return results;
	}

	/**
	 * QueryBuilders ���в�ѯ���
	 */
	public List<Map<String, Object>> queryForObjectEq(String type,
			String[] fields, String content, String sortField, SortOrder order,
			int from, int size) {
		SearchRequestBuilder reqBuilder = client.prepareSearch(esIndexName)
				.setTypes(type).setSearchType(SearchType.DEFAULT)
				.setExplain(true);
		QueryStringQueryBuilder queryString = QueryBuilders.queryString("\""
				+ content + "\"");
		for (String k : fields) {
			queryString.field(k);
		}
		queryString.minimumShouldMatch("10");
		reqBuilder.setQuery(QueryBuilders.boolQuery().must(queryString))
				.setExplain(true);
		if (StringUtils.isNotEmpty(sortField) && order != null) {
			reqBuilder.addSort(sortField, order);
		}
		if (from >= 0 && size > 0) {
			reqBuilder.setFrom(from).setSize(size);
		}

		SearchResponse resp = reqBuilder.execute().actionGet();
		SearchHit[] hits = resp.getHits().getHits();

		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		for (SearchHit hit : hits) {
			results.add(hit.getSource());
		}
		return results;
	}

	/**
	 * ������ּǲ�������Щ��,Ȼ��Ž�ȥ��ѯ
	 * 
	 * @author �߹���
	 * @date 2015��6��16�� ����9:56:08
	 * @param type
	 * @param field
	 * @param countents
	 * @param sortField
	 * @param order
	 * @param from
	 * @param size
	 * @return
	 */
	public List<Map<String, Object>> queryForObjectNotEq(String type,
			String field, Collection<String> countents, String sortField,
			SortOrder order, int from, int size) {

		SearchRequestBuilder reqBuilder = client.prepareSearch(esIndexName)
				.setTypes(type).setSearchType(SearchType.DEFAULT)
				.setExplain(true);
		List<String> contents = new ArrayList<String>();
		for (String content : countents) {
			contents.add("\"" + content + "\"");
		}
		TermsQueryBuilder inQuery = QueryBuilders.inQuery(field, contents);
		inQuery.minimumShouldMatch("10");
		reqBuilder.setQuery(QueryBuilders.boolQuery().mustNot(inQuery))
				.setExplain(true);
		if (StringUtils.isNotEmpty(sortField) && order != null) {
			reqBuilder.addSort(sortField, order);
		}
		if (from >= 0 && size > 0) {
			reqBuilder.setFrom(from).setSize(size);
		}

		SearchResponse resp = reqBuilder.execute().actionGet();
		SearchHit[] hits = resp.getHits().getHits();

		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		for (SearchHit hit : hits) {
			results.add(hit.getSource());
		}
		return results;
	}

	/**
	 * Filters ��ѯ��ʽ
	 * 
	 * 1. 1)QueryBuilders.queryString ��û�����ѯ
	 *    2)FilteredQueryBuilder query = QueryBuilders.filteredQuery(queryString,FilterBuilder)
	 *    3)ͨ�������װ��Ϊ��ѯ,�����query���뵽reqBuilder��;��ɲ���
	 *    
	 * 2.��   reqBuilder.setQuery(query);
	 * 
	 * 3.������2)�е�FilterBuilder���ֹ��췽ʽ-���������Դ�String���ͼ���
	 * FilterBuilders.rangeFilter("taskState").lt(20) С�� �� lte(20) С�ڵ���
	 * FilterBuilders.rangeFilter("taskState").gt(20)) ����  �� gte(20) ���ڵ���
	 * FilterBuilders.rangeFilter("taskState").from(start).to(end)) ��Χ,Ҳ����ָ������,���ַ�����ok��
	 * @author �߹���
	 * @date 2015��6��15�� ����10:06:05
	 * @param type
	 * @param field
	 * @param countents
	 * @param sortField
	 * @param order
	 * @param from
	 * @param size
	 * @return
	 */
	public List<Map<String, Object>> queryForObjectForElasticSerch(String type,
			String field, String content,int start,int end) {

		SearchRequestBuilder reqBuilder = client.prepareSearch(esIndexName)
				.setTypes(type).setSearchType(SearchType.DEFAULT)
				.setExplain(true);
		QueryStringQueryBuilder queryString = QueryBuilders.queryString("\""
				+ content + "\"");
			queryString.field(field);
		queryString.minimumShouldMatch("10");
		
		reqBuilder.setQuery(QueryBuilders.filteredQuery(queryString, FilterBuilders.rangeFilter("taskState").from(start).to(end)))
				.setExplain(true);

		SearchResponse resp = reqBuilder.execute().actionGet();
		SearchHit[] hits = resp.getHits().getHits();

		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		for (SearchHit hit : hits) {
			results.add(hit.getSource());
		}
		return results;
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("init...");

	}

}
