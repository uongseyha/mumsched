package mumsched;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import mumsched.dao.AdminDao;
import mumsched.model.Admin;

@Transactional
public class AdminDaoTest extends AbstractTest{
	@Autowired (required=false)
    private AdminDao adminDao;

    @After
    public void tearDown() {
    }

    @Test
    public void testFindAll() {

        List<Admin> list = adminDao.findAll();

        Assert.assertNotNull("failure - expected not null", list);
        Assert.assertEquals("failure - expected list size", 1, list.size());

    }
}
