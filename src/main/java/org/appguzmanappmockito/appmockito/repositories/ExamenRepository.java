package org.appguzmanappmockito.appmockito.repositories;

import java.util.List;
import org.appguzmanappmockito.appmockito.model.Examen;

public abstract interface ExamenRepository
{
  public abstract Examen guardar(Examen paramExamen);
  
  public abstract List<Examen> findAll();
}
