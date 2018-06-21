package com.yogo.fm.solr;

import com.yogo.fm.annotation.SolrField;
import com.yogo.fm.mapper.system.IAccountMapper;
import com.yogo.fm.model.system.AccountEntity;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @author qiqiang
 * @Description
 * @date 2018-06-20
 */
@Component
public class AccountSolrClient implements ISolrHelper {

    private final SolrClient solrClient;
    private final IAccountMapper accountMapper;

    @Autowired
    public AccountSolrClient(SolrClient solrClient, IAccountMapper accountMapper) {
        this.solrClient = solrClient;
        this.accountMapper = accountMapper;
    }

    @Override
    public SolrDocument find(Long id) {
        return null;
    }

    @Override
    public void update(Long id) throws Exception {
        AccountEntity accountEntity = accountMapper.find(id);
        if (accountEntity==null){
            return;
        }
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id",accountEntity.getId());
        Field [] fields = FieldUtils.getFieldsWithAnnotation(AccountEntity.class,SolrField.class);
        for (Field field:fields){
            document.addField(field.getName(),PropertyUtils.getProperty(accountEntity,field.getName()));
        }
        solrClient.add("collection1",document);
        solrClient.commit("collection1");
    }
}
