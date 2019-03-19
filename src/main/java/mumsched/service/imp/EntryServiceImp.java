package mumsched.service.imp;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mumsched.dao.EntryDao;
import mumsched.model.Entry;
import mumsched.service.EntryService;


@Service
public class EntryServiceImp implements EntryService {

	@Autowired
	EntryDao entryDao;

	@Override
	public void save(Entry entry) {
		entryDao.save(entry);
		return;
		
	}

	@Override
	public Entry getEntryByEntryID(Long entryid) {
		// TODO Auto-generated method stub
		return entryDao.findEntryByEntryID(entryid);
	}

	@Override
	public Entry gEntryBEntryName(String entryname) {
		// TODO Auto-generated method stub
		return entryDao.findEntryByEntryName(entryname);
	}

	@Override
	public List<Entry> getAllEntry() {
		// TODO Auto-generated method stub
		return entryDao.getAllEntry();
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		entryDao.deleteById(id);
	}
	}


