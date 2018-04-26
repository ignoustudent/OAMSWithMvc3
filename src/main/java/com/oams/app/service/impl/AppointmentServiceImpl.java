/**
 * 
 */
package com.oams.app.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oams.app.dao.AppointmentDao;
import com.oams.app.dao.BookingCapacityDao;
import com.oams.app.dao.DepartmentDao;
import com.oams.app.dao.DoctorDao;
import com.oams.app.dao.HospitalDao;
import com.oams.app.entities.AppointmentDetail;
import com.oams.app.entities.BookingCapacity;
import com.oams.app.entities.Department;
import com.oams.app.entities.Doctor;
import com.oams.app.entities.Hospital;
import com.oams.app.service.AppointmentService;

/**
 * @author RAKESH SINGH
 *
 * Apr 10, 2018
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentDao appointDao;
	
	@Autowired
	private HospitalDao hospitalDao;
	
	@Autowired 
	private DepartmentDao departmentDao;
	
	@Autowired
	private DoctorDao doctorDao;
	
	@Autowired
	private BookingCapacityDao bookingCapacityDao;
	
	@Override
	public AppointmentDetail bookAppointMent(AppointmentDetail appointmentDetails) {
		
		Hospital hospital = hospitalDao.getHospitalById(appointmentDetails.getHosId());
		Department department = departmentDao.getDepartmentById(appointmentDetails.getDeptId());
		List<Doctor> doctors = doctorDao.getDoctoryByHotelAndDeptId(hospital.getId(), department.getId());
		
		if(doctors !=null && doctors.size()>0){
			
			int maximum = doctors.size();
			int minimum=0;
			Random rn = new Random();
			int range = maximum - minimum;
			int randomNum =  rn.nextInt(range) + minimum;
			appointmentDetails.setDoctor(doctors.get(randomNum));
		}
		appointmentDetails.setHospital(hospital);
		appointmentDetails.setDepartment(department);
		
		return appointDao.save(appointmentDetails);
	}

	@Override
	public List<AppointmentDetail> getAppointmentDetailByEmail(String email) {
		
		return appointDao.getAppointmentDetailByEmail(email);
	}

	@Override
	public AppointmentDetail getAppointmentById(Integer id) {
		AppointmentDetail detail= appointDao.appointmentById(id);
		
			return detail;
		
	}

	
	@Override
	public List<LocalDate> getAvailability(Integer hospitalId, Integer departmentId) {
		
		Date date = new Date();
		List<AppointmentDetail> appointments = appointDao.getAppointmentsBYHandDID(hospitalId, departmentId, date);
		Calendar calendar =  Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 30);
		Date endDate = calendar.getTime();
		BookingCapacity capacity = bookingCapacityDao.getCapacityByHosAndDeptId(hospitalId, departmentId);
		List<LocalDate> dateList = new ArrayList<>();
		Map<AppointmentDetail, Integer> appDetailMap = new HashMap<>();
		

		while((endDate.compareTo(date))>=1){
			
			LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			for(AppointmentDetail detail : appointments){
				
				LocalDate bookingDate = detail.getAppointmentDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				if((bookingDate.compareTo(localEndDate))==0){
					
					detail.setAppointDateInLocalFormat(bookingDate);
					if(appDetailMap.containsKey(detail)){
						
						Integer key = appDetailMap.get(detail);
						key += 1;
						appDetailMap.put(detail, key);
					}else{
						
						appDetailMap.put(detail, 1);
					}
				}
			}
			dateList.add(localEndDate);
			Calendar cal =  Calendar.getInstance();
			cal.setTime(endDate);
			cal.add(Calendar.DATE, -1);
			endDate = cal.getTime();
			
		}
		
		for(Entry<AppointmentDetail, Integer>entry:appDetailMap.entrySet()){
			
			if(entry.getValue()>=capacity.getMaxBookingPerDay()){
				
		      dateList.remove(entry.getKey().getAppointDateInLocalFormat());
			}
		}
		
		if(dateList.size()>0)
			return dateList;
		return null;
	}

	
	@Override
	public List<AppointmentDetail> getAllAppointment() {
		
		return appointDao.getAllAppointment();
	}

}
