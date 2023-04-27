using System.Text.Json.Serialization;

namespace Ticket.Api.Models;

public partial class Clientes
{
    public int ClienteId { get; set; }

    public string Nombres { get; set; } = string.Empty;
    public string Clave { get; set; } = string.Empty;


    public Configuraciones Configuracion { get; set; }
    public int ConfiguracionId { get; set; }

    [JsonIgnore]
    public virtual ICollection<Tickets> Tickets { get; } = new List<Tickets>();

    [JsonIgnore]
    public virtual ICollection<Respuestas> Respuestas { get; } = new List<Respuestas>();
}
