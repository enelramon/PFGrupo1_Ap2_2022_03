using System;
using System.Collections.Generic;

namespace Ticket.Api.Models;

public partial class Sistemas
{
    public int SistemaId { get; set; }

    public string? Sistema1 { get; set; }

    public virtual ICollection<Tickets> ETickets { get; } = new List<Tickets>();
}
