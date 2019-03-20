package mumsched.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import mumsched.model.EntryBlock;
import mumsched.model.FacultyCourse;

public interface EntryBlockService {
	public void save(EntryBlock entryBlock);
	public EntryBlock getEntryBlockById(Long id);
	public List<EntryBlock> getAllEntryBlock();
	public void deleteById(Long id);
	public Page<EntryBlock> findPaginated(Pageable page);
	
}
