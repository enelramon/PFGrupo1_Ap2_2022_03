using System;
using System.Collections.Generic;

namespace Ticket.Api.Models;

public partial class Estatus
{
    public int EstatusId { get; set; }
    public string? Estatus1 { get; set; }
    public virtual ICollection<Tickets> Tickets { get; } = new List<Tickets>();
}
