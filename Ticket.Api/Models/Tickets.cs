using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace Ticket.Api.Models;
#pragma warning disable CS8602
public partial class Tickets
{
    public int TicketId { get; set; }
    public DateTime FechaCreacion { get; set; } = DateTime.Now;
    public DateTime? FechaModificacion { get; set; }

    
    public Clientes? Cliente { get; set; }
    public int ClienteId { get; set; }

    
    public Sistemas? Sistema { get; set; }
    public int SistemaId { get; set; }


    public Tipos? Tipo { get; set; }
    public int TipoId { get; set; }

  
    public Prioridades? Prioridad { get; set; }
    public int PrioridadId { get; set; }

    
    public Estatus? Estatus { get; set; }
    public int EstatusId { get; set; }


    public string Especificaciones { get; set; } = string.Empty;
    public DateTime? FechaFinalizado { get; set; } 
    public virtual ICollection<Respuestas> Respuestas { get; } = new List<Respuestas>();
}
