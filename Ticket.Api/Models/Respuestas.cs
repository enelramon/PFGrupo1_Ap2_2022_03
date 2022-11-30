using Microsoft.EntityFrameworkCore.Storage.ValueConversion.Internal;
using System.ComponentModel.DataAnnotations;

namespace Ticket.Api.Models
{
    public class Respuestas
    {
        [Key]
        public int RespuestaId { get; set; }
        public string? Respuesta { get; set; }
        public int? ClienteId { get; set; }
        public DateTime? Fecha { get; set; }
        public int? TicketId { get; set; }
    }
}
