package com.example.imserver.service;

import com.example.imserver.controller.vo.ContactDetailVO;

public interface ContactService {

    ContactDetailVO contactDetail(Long uid);
}
