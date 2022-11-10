using System;
using System.Collections.Generic;

namespace Ticket.Api.Models;

public partial class Tipos
{
    public int TipoId { get; set; }

    public string? Tipo1 { get; set; }

    public virtual ICollection<Tickets> ETickets { get; } = new List<Tickets>();
}
