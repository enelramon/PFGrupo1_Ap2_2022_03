﻿using System;
using System.Collections.Generic;

namespace Ticket.Api.Models;

public partial class Tipos
{
    public int TipoId { get; set; }
    public string? Tipo { get; set; }
    public virtual ICollection<Tickets> Tickets { get; } = new List<Tickets>();
}
