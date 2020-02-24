package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookRepository guestbookRepository; 
	
	public List<GuestbookVo> getAllBoard() {
		return guestbookRepository.findAll();
	}
	
	public boolean add(GuestbookVo vo) {
		boolean count = guestbookRepository.insert(vo);
		return count;
	}

	public boolean delete(GuestbookVo vo) {
		boolean count = guestbookRepository.delete(vo);
		return count;
	}
	
}
