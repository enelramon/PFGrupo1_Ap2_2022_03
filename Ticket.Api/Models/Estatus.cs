using System;
using System.Collections.Generic;

namespace Ticket.Api.Models;

public partial class Estatus
{
    public int EstatusId { get; set; }
    public string Estado { get; set; } = String.Empty;
}
