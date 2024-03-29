package com.mx.simlb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.simlb.dao.ReservacionDAO;
import com.mx.simlb.dto.Reservaciones;
import com.mx.simlb.entity.HorariosReservados;
import com.mx.simlb.service.ReservacionService;
import com.mx.simlb.vo.ReservacionesVO;

@Service
public class ReservacionServiceImpl implements ReservacionService{

	@Autowired
	private ReservacionDAO reservasionDAO;
		
	public Boolean reservarCita(ReservacionesVO reservacionVO )throws Exception{
		Boolean regExitoso = false;
		
		try{					
			Reservaciones reservacion = new Reservaciones();
			
			reservacion.setNombrePersona(reservacionVO.getNombrePersona());
			reservacion.setMotivo(reservacionVO.getMotivo());
			reservacion.setFechaReservada(reservacionVO.getFechaReservada());
			reservacion.setHoraIni(reservacionVO.getHoraIni());
			reservacion.setHoraFin(reservacionVO.getHoraFin());
			
			
			regExitoso = reservasionDAO.registrarReservacion(reservacion);
			
		}catch(Exception ex){
			throw new Exception("Service_Error :  registrarReservacion() "+ex.getMessage());
		}
		
		return regExitoso;
	}
	
	public Boolean actualizarReservacion(ReservacionesVO reservacionesVO )throws Exception{
		Boolean actualizacionExitosa = false;
		
		try{					
			Reservaciones reservacion = new Reservaciones();
			
			reservacion.setIdReservaciones(reservacionesVO.getIdReservaciones());
			reservacion.setNombrePersona(reservacionesVO.getNombrePersona());
			reservacion.setMotivo(reservacionesVO.getMotivo());
			reservacion.setFechaReservada(reservacionesVO.getFechaReservada());
			reservacion.setHoraIni(reservacionesVO.getHoraIni());
			reservacion.setHoraFin(reservacionesVO.getHoraFin());
			reservacion.setIdHorariosReservados(reservacionesVO.getIdHorariosReservados());
			reservacion.setIdHorariosDisponiblesIni(reservacionesVO.getIdHorariosDisponiblesIni());
			reservacion.setIdHorariosDisponiblesFin(reservacionesVO.getIdHorariosDisponiblesFin());
			
			actualizacionExitosa = reservasionDAO.actualizarReservacion(reservacion);
			
		}catch(Exception ex){
			throw new Exception("Service_Error :  actualizarReservacion() "+ex.getMessage());
		}
		
		return actualizacionExitosa;
	}
	
	@Override
	public List<ReservacionesVO> cargarCitas()throws Exception{
		
		List<ReservacionesVO> reservacionList = new ArrayList<ReservacionesVO>();
		
		try{
			
			reservacionList = reservasionDAO.cargarCitas();					
			
		}catch(Exception ex){
			throw new Exception("Service_Error :  cargarCitas() "+ex.getMessage());
		}
		
		return reservacionList;
	}

	@Override
	public Boolean eliminarCita(Long idHorarioReservado)throws Exception{
		
		try{
			Boolean exito = false;
			
			exito = reservasionDAO.eliminarCita(idHorarioReservado);
			
			
			return exito;
		}catch(Exception ex){
			throw new Exception("Service_Error :  eliminarCita() "+ex.getMessage());
		}

	}
	
	@Override
	public com.mx.simlb.entity.HorariosReservados obteberHorariosReservadosById(Long idHorarioReservado)throws Exception{
		
		try{
			com.mx.simlb.entity.HorariosReservados horariosReserv = null;
			
			horariosReserv = reservasionDAO.obteberHorariosReservadosById(idHorarioReservado);
			
			
			return horariosReserv;
		}catch(Exception ex){
			throw new Exception("Service_Error :  obteberHorariosReservadosById() "+ex.getMessage());
		}
		
		
	}
	
}
