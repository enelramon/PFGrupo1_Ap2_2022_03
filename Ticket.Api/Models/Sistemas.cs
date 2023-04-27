using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace Ticket.Api.Models;

public partial class Sistemas
{
    public int SistemaId { get; set; }
    public string Sistema { get; set; } = string.Empty;
    [JsonIgnore]
    public virtual ICollection<Tickets> Tickets { get; } = new List<Tickets>();

    public int TicketsCount { get { return Tickets.Count ; } }
}
