using Microsoft.EntityFrameworkCore.Storage.ValueConversion.Internal;
using System.ComponentModel.DataAnnotations;
using System.Text.Json.Serialization;

namespace Ticket.Api.Models
{
    public class Respuestas
    {
        [Key]
        public int RespuestaId { get; set; }
        public string Contenido { get; set; } = string.Empty;

        
        public Clientes? Cliente { get; set; }
        public int ClienteId { get; set; }

        public DateTime Fecha { get; set; } = DateTime.Now;

        [JsonIgnore]
        public Tickets? Ticket { get; set; }
        public int TicketId { get; set; }

    }
}
