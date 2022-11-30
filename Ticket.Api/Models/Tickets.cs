using System;
using System.Collections.Generic;

namespace Ticket.Api.Models;

public partial class Tickets
{
    public int TicketId { get; set; }

    public DateTime? FechaCreacion { get; set; }

    public int? ClienteId { get; set; }

    public int? SistemaId { get; set; }

    public int? TipoId { get; set; }

    public int? PrioridadId { get; set; }

    public int? EstatusId { get; set; }

    public string? Especificaciones { get; set; }

    public DateTime? FechaFinalizado { get; set; }

    public int? Orden { get; set; }
    public virtual ICollection<Respuestas> Respuestas { get; } = new List<Respuestas>();
}
