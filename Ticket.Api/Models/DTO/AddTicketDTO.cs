namespace Ticket.Api.Models.DTO
{
    public class AddTicketDTO
    {
        public int ClienteId { get; set; }
        public int SistemaId { get; set; }
        public int TipoId { get; set; }
        public int PrioridadId { get; set; }
        public string Especificaciones { get; set; } = string.Empty;
    }
}

