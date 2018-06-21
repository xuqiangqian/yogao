package com.yogo.fm.solr;

import org.apache.solr.common.SolrDocument;

/**
 * @author qiqiang
 * @Description
 * @date 2018-06-20
 */
public interface ISolrHelper {
    SolrDocument find(Long id);

    void update(Long id) throws Exception;
}
