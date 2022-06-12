package it.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.siw.catering.model.Piatto;
import it.siw.catering.repository.PiattoRepository;

@Service
public class PiattoService {

	@Autowired
	private PiattoRepository piattoRepository;

	public List<Piatto> getPiatti() {

		List<Piatto> piatti = new ArrayList<>();

		for (Piatto piatto : this.piattoRepository.findAll()) {
			piatti.add(piatto);
		}

		return piatti;
	}

	public boolean addPiatto(Piatto piatto) {

		Piatto piattoSaved = this.piattoRepository.save(piatto);
		return piattoSaved != null;
	}

	public Piatto getPiattoByNome(String nomePiatto) {

		return this.piattoRepository.findByNome(nomePiatto).get();
	}

	public Piatto getPiattoById(Long idPiatto) {

		return this.piattoRepository.findById(idPiatto).get();
	}

	public void deletePiattoById(Long idPiatto) {

//		Dovresti fare un controllo e restituire un booleano
		this.piattoRepository.deleteById(idPiatto);
	}

}
