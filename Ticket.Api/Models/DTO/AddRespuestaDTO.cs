namespace Ticket.Api.Models.DTO
{
    public class AddRespuestaDTO
    {
        public int ClienteId { get; set; }
        public int TicketId { get; set; }
        public string Contenido { get; set; } = string.Empty;
    }
}
