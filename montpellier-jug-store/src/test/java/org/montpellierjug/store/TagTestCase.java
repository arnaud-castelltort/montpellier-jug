package org.montpellierjug.store;

import org.jooq.Configuration;
import org.jooq.ConnectionProvider;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DefaultConfiguration;
import org.junit.Test;
import org.montpellierjug.store.jooq.tables.daos.TagDao;
import org.montpellierjug.store.jooq.tables.pojos.Tag;

import java.sql.Connection;

/**
 * Created by chelebithil on 06/03/15.
 */
public class TagTestCase extends DaoTestCase {



    @Test
    public void all(){
        jooq(conf -> {

            TagDao tagDao = new TagDao(conf);
            for (Tag tag : tagDao.findAll()) {
                LOGGER.debug(tag.getId() + ": " + tag.getName());
            }
            if(tagDao.fetchByName("jooq").isEmpty()){
                LOGGER.debug("Inserting jooq tag");
                Tag tag = new Tag();
                tag.setName("jooq");
                tagDao.insert(tag);
            }

        });
    }

}
