using System;
using System.Collections.Generic;

namespace Ticket.Api.Models;

public partial class Sistemas
{
    public int SistemaId { get; set; }

    public string? Sistema { get; set; }

    public virtual ICollection<Tickets> Tickets { get; } = new List<Tickets>();
}
