using System;
using System.Collections.Generic;

namespace Ticket.Api.Models;

public partial class Clientes
{
    public int ClienteId { get; set; }

    public string? Nombres { get; set; }

    public virtual ICollection<Tickets> Tickets { get; } = new List<Tickets>();
}
