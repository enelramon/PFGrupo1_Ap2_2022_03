using System;
using System.Collections.Generic;

namespace Ticket.Api.Models;

public partial class Prioridades
{
    public int PrioridadId { get; set; }

    public string? Prioridad { get; set; }

    public virtual ICollection<Tickets> ETickets { get; } = new List<Tickets>();
}
