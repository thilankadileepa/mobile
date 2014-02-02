package com.dushantha.business;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.dushantha.dao.OperationDAO;
import com.dushantha.dao.OperationDAOIMPL;
import com.dushantha.dto.EventUpdateDTO;
import com.dushantha.entities.EventEntity;
import com.dushantha.util.EntityConverter;

public class OperationIMPL implements Operation {
	private OperationDAO operaionDAO;

	@Override
	public boolean editEvent(EventUpdateDTO eventUpdateDTO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEvent(Integer eventId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveEvent(final Context context,
			EventUpdateDTO eventUpdateDto) {
		operaionDAO = new OperationDAOIMPL();
		return operaionDAO.saveEvent(context,
				EntityConverter.getEventEntityFromDTO(eventUpdateDto));
	}

	@Override
	public List<EventUpdateDTO> getAllEvents(Context context) {
		operaionDAO = new OperationDAOIMPL();
		List<EventUpdateDTO> eventUpdateDTOs = new ArrayList<EventUpdateDTO>();
		for (EventEntity eventEntity : operaionDAO.getAllEvents(context)) {
			EventUpdateDTO eventUpdateDTO = new EventUpdateDTO(eventEntity);
			eventUpdateDTOs.add(eventUpdateDTO);
		}
		return eventUpdateDTOs;
	}

}
