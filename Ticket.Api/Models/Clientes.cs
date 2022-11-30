using System;
using System.Collections.Generic;

namespace Ticket.Api.Models;

public partial class Clientes
{
    public int ClienteId { get; set; }

    public string? Nombres { get; set; }
    public string? Clave { get; set; }
    public int ConfiguracionId { get; set; }
    public virtual ICollection<Tickets> Tickets { get; } = new List<Tickets>();
    public virtual ICollection<Respuestas> Respuestas { get; } = new List<Respuestas>();
}
