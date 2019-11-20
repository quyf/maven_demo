package cn.quyf.demo.spring.sequence;

import cn.quyf.demo.spring.sequence.SequenceConst.TableName;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

public class SequenceDaoTest extends BaseTests{

	@Autowired
	private ISequenceDao sequenceDao;
	
	@Test
	public void test_nextId() {
		
		long id = sequenceDao.getNextId(TableName.ORDER);
		
		assertTrue(id > 0);
			
	}
}
