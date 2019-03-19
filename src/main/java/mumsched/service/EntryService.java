package mumsched.service;

import java.util.List;


import mumsched.model.Entry;


public interface EntryService {
	public void save(Entry entry);
	public Entry getEntryByEntryID(Long entryid);
	public Entry gEntryBEntryName(String entryname);
	public List<Entry> getAllEntry();
	public void deleteById(Long id);
}
