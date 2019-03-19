package mumsched.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mumsched.model.Block;
import mumsched.model.Section;

public interface SectionService {
	public void save(Section section);
	public Section getSectionBySectionId(Long id);
	public Section geSectionBSectionBlockId(Block block);
	public List<Section> getAllSections();
	public void deleteById(Long id);
	public Page<Section> findPaginated(Pageable pageable);
}
